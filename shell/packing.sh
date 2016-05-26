#!/bin/bash
source /etc/profile
SELF=$(cd $(dirname $0); pwd -P)/$(basename $0)
SELF_DIR=`dirname $SELF`

#project no
DWPROJECTNO="rapidcron"

#build file name
BUILD_FILE_NAME="${DWPROJECTNO}-`date +%Y%m%d-%H%M`.tgz"


#clean old build
echo "==============================***clean old build***======================================="
[[ ! -d  /data/release/${DWPROJECTNO}/dist  ]] && mkdir -p /data/release/${DWPROJECTNO}/dist ;

if [ -d  /data/release/${DWPROJECTNO}/work ] ; then 
   rm -rf /data/release/${DWPROJECTNO}/work/*
fi
#create app dir
mkdir -p /data/release/${DWPROJECTNO}/work/config/
mkdir -p /data/release/${DWPROJECTNO}/work/web/
mkdir -p /data/release/${DWPROJECTNO}/work/lib/
mkdir -p /data/release/${DWPROJECTNO}/work/htdocs/
mkdir -p /data/release/${DWPROJECTNO}/work/shell/
mkdir -p /data/release/${DWPROJECTNO}/work/init/

#build
echo "==============================*** build project ***======================================="
cd /data/release/${DWPROJECTNO}/src/
svn up
mvn -Dmaven.test.skip=true -am clean package

#copy
echo "==============================***cp to /data/release/${DWPROJECTNO}/work ***======================================"
#copy *.war *.jar and config
find . -maxdepth 4 -regex ".*/target/[^\/]*.war"  -exec cp -f {} /data/release/${DWPROJECTNO}/work/web/ \;
find . -maxdepth 4 -regex ".*/target/[^\/]*.jar"  -exec cp -f {} /data/release/${DWPROJECTNO}/work/lib/ \;
cp -Rf  config/* /data/release/${DWPROJECTNO}/work/config/

#copy http static elements
if [ -d "/data/release/${DWPROJECTNO}/src/src/main/webapp/" ] ; then
    cp -Rf /data/release/${DWPROJECTNO}/src/src/main/webapp/* /data/release/${DWPROJECTNO}/work/htdocs/
fi
for pmodule in `ls  /data/release/${DWPROJECTNO}/src/` ; do
   if [ -d "/data/release/${DWPROJECTNO}/src/${pmodule}/src/main/webapp/" ] ; then
      mkdir -p /data/release/${DWPROJECTNO}/work/htdocs/${pmodule}/
	  cp -Rf /data/release/${DWPROJECTNO}/src/${pmodule}/src/main/webapp/* /data/release/${DWPROJECTNO}/work/htdocs/${pmodule}/
   fi
done
#del WEB-INF *.jsp
find /data/release/${DWPROJECTNO}/work/htdocs/ -type "d"  -name "WEB-INF" | xargs rm -rf
find /data/release/${DWPROJECTNO}/work/htdocs/ -type "f"  -name "*.jsp" | xargs rm -rf

#copy shell
cp -Rf /data/release/${DWPROJECTNO}/src/shell/* /data/release/${DWPROJECTNO}/work/shell/
rm -f /data/release/${DWPROJECTNO}/work/shell/packing.sh
find /data/release/${DWPROJECTNO}/work/shell/ -name "*\.sh"  -exec chmod +x {} \;

#copy init data
if [ -d "/data/release/${DWPROJECTNO}/src/doc/initdata/" ] ; then
  cp -Rf /data/release/${DWPROJECTNO}/src/doc/initdata/* /data/release/${DWPROJECTNO}/work/init/
fi

#del .svn
find /data/release/${DWPROJECTNO}/work/ -type "d"  -name "*.svn" | xargs rm -rf


#tar
chown :${DWPROJECTNO} /data/release/${DWPROJECTNO}/* -R
chmod 755 -R /data/release/${DWPROJECTNO}/work/
cd /data/release/${DWPROJECTNO}/work/
tar -zcf ${BUILD_FILE_NAME} *
mv -i ${BUILD_FILE_NAME} /data/release/${DWPROJECTNO}/dist/
cp -f /data/release/${DWPROJECTNO}/dist/${BUILD_FILE_NAME} /data/release/${DWPROJECTNO}/dist/${DWPROJECTNO}.tgz
echo "==============================***  Packing Done ***======================================="

