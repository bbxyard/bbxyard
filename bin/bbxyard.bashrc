# bbxyard bash run script
# define some useful functions
# @author boxu
# @create 2015.11.19


# SYSTEM ERROR-CODE
ENOENT=2    # No such file or directory
E2BIG=7     # Argument list too long
EACCES=13   # Permission denied
EEXIST=17   # File exists
EINVAL=22   # Invalid argument

# user error code
ERR_NEED_ARGUMENT=$EINVAL
ERR_NOT_EXIST=$ENOENT


# color echo
NORMAL=$(tput sgr0)
RED=$(tput setaf 1; tput bold)
BLUE=$(tput setaf 4; tput bold)
GREEN=$(tput setaf 2; tput bold)
YELLOW=$(tput setaf 3; tput bold)
function red()    { echo -e "$RED$*$NORMAL"; }
function blue()   { echo -e "$BLUE$*$NORMAL"; }
function green()  { echo -e "$GREEN$*$NORMAL"; }
function yellow() { echo -e "$YELLOW$*$NORMAL"; }


# safe tmp work dir
THIS_SCRIPT=$0
[ "${THIS_SCRIPT:0:1}" == "-" ] && THIS_SCRIPT=${THIS_SCRIPT:1}
TMP_DIR=$HOME/var/run/$(basename "$THIS_SCRIPT").$$
function do_init_tmp_dir() { mkdir -p "$TMP_DIR"; }
function do_fini_tmp_dir() { rm -rvf  "$TMP_DIR"; }


# sudo wrap
function invoke_sudo()
{
    # sent the pass and invoke a null command once.
    echo $SUDO_PASSWD | sudo -S cat /dev/null
    # execute the real command
    sudo $@
}

# test can I have root power!
function amiroot()
{
    ROOT_UID=0
    echo "input $1"
    if [ "$UID" = "$ROOT_UID" ]; then
        echo "You are root"
    else
        echo "You are just an ordinary user(but mom loves you too)."
    fi
}

# verify and re-symbol dir link
function verify_dir_symlink()
{
	src_file=$1
	dst_link=$2

	# if dir-link exists unlink
	[ -h "$dst_link" ] && unlink "$dst_link"

	# if dir-real exists rename with date
	if [ -d "$dst_link" ]; then
		NOW=$(date +%Y-%m-%d-%H-%M-%S)
		mv "$dst_link" "$dst_link.$NOW"
	fi

	# re-link
	[ ! -d "$dst_link" ] && ln -s "$src_file" "$dst_link"
}

# get os release name
function get_os_name()
{
    RETVAL=0
    for ((i=0; i<1; ++i))
    {
        os_type=$(uname -s)
        case "$os_type" in
            Linux)
                fr=/etc/issue
                grep -io Ubuntu     "$fr" && break;
                grep -io LinuxMint  "$fr" && break;
                grep -io Redhat     "$fr" && break;
                grep -io CentOS     "$fr" && break;
                grep -io Suse       "$fr" && break;
                ;;
            Darwin)  echo "Darwin"  ;;
            FreeBSD) echo "FreeBSD" ;;
            *)
                echo "Unknown"
                RETVAL=-1
                ;;
        esac
    }
    return $RETVAL
}

# get user bash profile
function get_user_bash_profile()
{
    os_name=$(get_os_name)
    case "$os_name" in
        Ubuntu|Mint|Suse)
            echo "$HOME/.bashrc"
            ;;
        *)
            echo "$HOME/.bash_profile"
            ;;
    esac
    return 0;
}


# change file ext
# e.g. chext /tmp/foobar.TXT txt --> /tmp/foobar.txt
function chext()
{
    [ -z "$1" -o -z "$2" ] && {
        echo "Usage chext <filepath> <ext>"
        return 22
    }
    [ ! -f "$1" ] && echo "NOT found $1" && return 2

    # remove surfix
    local dstname=${1%.*}
    # verify dst-ext include "."
    [ "${2:0:1}" != "." ] && dstname=$dstname"."
    dstname=$dstname"$2"
    mv "$1" "$dstname"
}


# * * * * * * * * * * * * *
# git operator
# * * * * * * * * * * * * *
# git commit and push
function git_auto_pom()
{
    git commit -am "$1"
    git push origin master
}

#
# git_sync: git pull origin master from any dir
#
function git_sync()
{
    if [ -z "$1" ]; then
        red "usage: git_sync <git_dir>"
        return $ERR_NEED_ARGUMENT
    fi

    # locate git dir
    local DNAME=$(basename $1)
    [ "$DNAME" == ".git" ] && DIR=$(dirname $1) || DIR=$1
    [ ! -d "$DIR/.git" ] && echo "$1 not an git dir pass!!" && exit 2

    # git pull and savelog
    [ ! -d ~/var/log ] && mkdir -p ~/var/log
    local LOG_FILE=~/var/log/git-sync.log
    echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync begin!!" | tee -ai $LOG_FILE
    git -C "$DIR" pull origin master 2>&1 | tee -ai $LOG_FILE
    local RETVAL=$?
    echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync end!! status=$RETVAL" | tee -ai $LOG_FILE
    return $RETVAL
}


# svn_sync: svn update
function svn_sync()
{
    if [ -z "$1" ]; then
        red "usage: svn_sync <svn_dir>"
        return $ERR_NEED_ARGUMENT
    fi
    local DIR=$1
    [ ! -d ~/var/log ] && mkdir -p ~/var/log
    local LOG_FILE=~/var/log/svn-sync.log
    echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync begin!!" | tee -ai $LOG_FILE
    svn up "$DIR" 2>&1 | tee -ai $LOG_FILE
    local RETVAL=$?
    echo $(date +"%Y-%m-%d %H:%M:%S")" $DIR sync end!! status=$RETVAL" | tee -ai $LOG_FILE
    return $RETVAL
}


# gbk to utf8 convert
function gbk2u8()
{
    local app=gbk2u8
    if [ -z "$1" ]; then
        red "convert gbk file to uft-8"
        red "usage: $app gbk-file"
        return $ERR_NEED_ARGUMENT
    fi
    local BAK=$1.bak
    iconv -f gbk -t utf-8 "$1" > "$BAK" && mv "$BAK" "$1"
    [ -f "$BAK" ] && rm "$BAK"
}
function g2u()
{
    gbk2u8 $*
}
