#!/bin/bash

#############################################################################################
##
##      Description: This is a project operation and maintenance update script.
##	Version :    v-0.1
##	Date:	     2012-05-17
##      The author:  liuyd
##      From:	     game.yy.com
## 
##############################################################################################

###
. /lib/lsb/init-functions
##
source /etc/profile
##
SELF=$(cd $(dirname $0); pwd -P)/$(basename $0)
SELF_DIR=`dirname $SELF`
#
## set args
#
rsync_server="package.yygamedev.com"
rsync_user="rapidcron"
rsync_passwd="rapidcronrelease"
resin_user="www-data"
resin_group="www-data"
app_dir="/data/app"
bak_dir="/data2/backup"
project_dir="$app_dir/$DWPROJECTNO"
bak_project_dir="$bak_dir/$DWPROJECTNO"
bak_time=`date +%F_%H_%M`

#
## judge args is null or not 
#
if [ -z $app_dir ] || [ -z $bak_dir ];then
	echo "\$app_dir or \$bak_dir is null !"
	exit 1
fi
#
## Judge directory exists or not 
#
if [ ! -d $app_dir ];then
        mkdir -p $app_dir
fi
if [ ! -d $project_dir ];then
	mkdir -p $project_dir
fi
if [ ! -d $bak_dir ];then
	mkdir -p $bak_dir
fi
if [ ! -d $bak_project_dir ];then
	mkdir -p $bak_project_dir
fi

# arguments $projectno $dwenv appoint at /etc/profile
if [ -z ${DWPROJECTNO} ];then
	echo "Argument \$DWPROJECTNO is null ! Please appoint \$DWPROJECTNO at (/etc/profile)"
	exit 1
fi
if [ -z ${DWENV} ];then
        echo "Argument \$DWENV is null ! Must be one of them in ' dev test prod '."
        exit 1
fi
DWENVARRAY=("dev" "test" "prod")
envmatch=$(echo ${DWENVARRAY[@]} | grep -o ${DWENV}) 
if [ -z  ${envmatch}  ] ;then
	echo "\$DWENV=${DWENV} is not correct ! Must be one of them in ' dev test prod '."
	exit 1
fi

## deploy 
function_deploy(){
	if [ -f "$bak_project_dir/$DWPROJECTNO.tgz" ];then
		rm -rf $app_dir/$DWPROJECTNO/*
		cd $app_dir/$DWPROJECTNO
		tar xzf $bak_project_dir/$DWPROJECTNO.tgz
	else
		echo "$bak_project/$DWPROJECTNO.tgz not exists!"
		exit 1
	fi
}

## back /data/app/$DWPROJECTNO
function_backup(){
        if [ -d $app_dir/$DWPROJECTNO ];then
                cd $app_dir
		tar czf $DWPROJECTNO.lastest.tgz $DWPROJECTNO
                mv $app_dir/$DWPROJECTNO.lastest.tgz $bak_project_dir/$DWPROJECTNO.lastest.tgz
                cp $bak_project_dir/$DWPROJECTNO.lastest.tgz $bak_project_dir/$bak_time.$DWPROJECTNO.tgz
	else
		echo "Local directory $app_dir/$DWPROJECTNO not exists!"
		return 1
	fi

}

## restore  /data/app/$DWPROJECTNO lastest version
function_restore(){
	if [ -f $bak_project_dir/$DWPROJECTNO.lastest.tgz ];then
        	cd $app_dir
		tar czf backup_before_restore.$DWPROJECTNO.$bak_time.tgz $DWPROJECTNO
		mv backup_before_restore.$DWPROJECTNO.$bak_time.tgz $bak_project_dir
		rm -rf $DWPROJECTNO/*
		###echo "tar xzf  $bak_project_dir/$DWPROJECTNO.lastest.tgz"
		tar xzf  $bak_project_dir/$DWPROJECTNO.lastest.tgz
	else
		echo "$bak_project_dir/$DWPROJECTNO.lastest.tgz not exist!"
		exit 1
	fi
}


## Rsync remote server  /data/app/$DWPROJECTNO
function_sync(){
        ## set rsync_passwd
        export RSYNC_PASSWORD=$rsync_passwd 
        # rsync project files
        rsync -vzrtopg --progress  $rsync_user@$rsync_server::$DWPROJECTNO $bak_dir/$DWPROJECTNO
}

case "$1" in 
        update ) 
		$SELF backup
		$SELF rsync
		log_daemon_msg "Deploy project: " "$DWPROJECTNO"
		function_deploy  >> /dev/null
		if [ $? -eq 0 ];then
			log_end_msg 0
	                chown -R $resin_user.$resin_group $app_dir
                	log_daemon_msg "Executing: chmod +x $app_dir/$DWPROJECTNO/shell/*.sh" ""
                	chmod +x $app_dir/$DWPROJECTNO/shell/*.sh
                	if [ $? -eq 0 ];then
                	        log_end_msg 0
                	else
                	        log_end_msg 1
                	fi
		else
			log_end_msg 1
			log_failure_msg "Please check $DWPROJECTNO.lastest.tgz is exists or not"
		fi
                ;;
	rsync)
		log_daemon_msg "Rsync remote file: $bak_project_dir/$DWPROJECTNO.tgz" "rsync from $rsync_server"
		function_sync >> /dev/null
                if [ $? -eq 0 ];then
                        log_end_msg 0
                else
                        log_end_msg 1
                        log_failure_msg "Please check $rsync_server configure file !!"
                fi
                ;;
	backup )
		log_daemon_msg "Backup local directory $app_dir/$DWPROJECTNO" ""
                function_backup >> /dev/null
                if [ $? -eq 0 ];then
                        log_end_msg 0
                else
                        log_end_msg 1
                        log_failure_msg "Please check $app_dir/$DWPROJECTNO exists or not !"
                fi
		;;
        restore )
		log_daemon_msg "Restore local lastest version :" "$DWPROJECTNO"
                function_restore
		if [ $? -eq 0 ];then
                        log_end_msg 0
                else
                        log_end_msg 1
                        log_failure_msg "Please check $app_dir/$DWPROJECTNO.lastest.tgz exist or not !!"
                fi
                ;;
        *)
                echo "Usage: $SELF { update | rsync | backup | restore}"
esac


