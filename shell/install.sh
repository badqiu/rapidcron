# 脚本名： install.sh 
# 说明：第一次安装的脚本，创建程序需要的目录，其他环境准备等。不包含软件的安装。
# install.sh 在最后需显示数据库、
#
# -------------------------------------------------
# 注意: 不允许在install脚本中编写 数据库初始化脚本调用
# -------------------------------------------------

source /etc/profile
mkdir -p /data/app/${DWPROJECTNO}/
mkdir -p /data/webapps/${DWPROJECTNO}/
mkdir -p /data/wwwdata/${DWPROJECTNO}/
mkdir -p /data2/backup/${DWPROJECTNO}/
mkdir -p /data2/log/${DWPROJECTNO}/
mkdir -p /data2/log/nginx/
mkdir -p /data2/log/resin/
mkdir -p /data2/log/mysql/

chown -R www-data.www-data  /data/app/${DWPROJECTNO}/
chown -R www-data.www-data  /data/webapps/${DWPROJECTNO}/
chown -R www-data.www-data  /data/wwwdata/${DWPROJECTNO}/
chown -R www-data.www-data  /data2/log/resin/
chown -R www-data.www-data  /data2/log/${DWPROJECTNO}/
chown -R www-data.www-data  /usr/local/resin/

#link sites
if [ -d "/data/app/${DWPROJECTNO}/config/${DWENV}/sites" ] ; then
   [[ -d  /usr/local/nginx/conf/sites  ]] && mv -f /usr/local/nginx/conf/sites /usr/local/nginx/conf/bak_sites ;
   ln -s /data/app/${DWPROJECTNO}/config/${DWENV}/sites /usr/local/nginx/conf/sites
fi 

#link resin.xml
if [ -f "/data/app/${DWPROJECTNO}/config/${DWENV}/resin.xml" ] ; then
   [[ -f  /usr/local/resin/conf/resin.xml  ]] && mv -f /usr/local/resin/conf/resin.xml /usr/local/resin/conf/bak_resin.xml ;
   ln -s /data/app/${DWPROJECTNO}/config/${DWENV}/resin.xml /usr/local/resin/conf/resin.xml
fi