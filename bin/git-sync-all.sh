#/bin/bash
[ -f ~/.bash_profile ] && . ~/.bash_profile || . ~/.bashrc

function git-sync()
{
  if [ -z "$1" ]; then
      red "usage: git_sync <git_dir>"
      return $ERR_NEED_ARGUMENT
  fi

  # locate git dir
  local DNAME=$(basename $1)
  [ "$DNAME" = ".git" ] && DIR=$(dirname $1) || DIR=$1
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

function do-sync-dir() {
  local ROOT_DIR=$1
  [ ! -d "$ROOT_DIR" ] && echo "NOT EXIST [$ROOT_DIR]" && return 1;
  time find "$ROOT_DIR" -name ".git" -type d | xargs -n1 git-sync
}

do-sync-dir /usr/local/xconf
do-sync-dir ~/devhome
do-sync-dir ~/data/sync/git.yh
do-sync-dir ~/data/sync/git.me
