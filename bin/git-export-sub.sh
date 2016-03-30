#!/bin/bash
#
# 同步导出到子项目
#

# 根路径
PROJ_ROOT_HOME=$(pushd $(dirname $0)/../ >/dev/null; pwd; popd >/dev/null);
echo "PROJ_ROOT_HOME=$PROJ_ROOT_HOME"

WBOX_REPO=git@github.com:bbxyard/wbox
WBOX_LOCAL=/tmp/wbox_local

function verify_empty_dir()
{
    if [ -d "$1" ]; then
        rm -rvf "$1"
    fi
    mkdir -p "$1"
}

function wbox_export()
{
    [ ! -d "$WBOX_LOCAL" ] && {
        mkdir -p $(dirname "$WBOX_LOCAL") &>/dev/null;
        git clone $WBOX_REPO "$WBOX_LOCAL"
    } || {
        git -C "$WBOX_LOCAL" pull origin master 2>&1
    }

    # 删除已有目录
    verify_empty_dir "$WBOX_LOCAL/lib"
    verify_empty_dir "$WBOX_LOCAL/yard/sdk/wbox"

    cp -rvf "$PROJ_ROOT_HOME/lib/cmake" "$WBOX_LOCAL/lib"
    cp -rvf "$PROJ_ROOT_HOME/yard/sdk/wbox" "$WBOX_LOCAL/yard/sdk"
    find "$WBOX_LOCAL" -type d -name "build*" -exec rm -rvf {} \;
    pushd "$WBOX_LOCAL" &>/dev/null
        git add .
        git commit -am "sync from bbxyard at $(date +%Y-%m-%d-%H-%S-%M)"
        git push origin master
    popd

    echo "done!!"
}


RETVAL=0
name="$1"
case "$name" in
    "wbox")
        wbox_export
        ;;
    "jbox"|"cbox")
        echo "un-impled!!"
        ;;
    *)
        echo "export bbxyard to sub-repo."
        echo "usage: $(basename $0) {wbox|jbox|cbox}"
        RETVAL=1
        ;;
esac

exit $RETVAL
