[ -f ~/.bash_profile ] && . ~/.bash_profile || . ~/.bashrc
time find ~/devhome/ -name "*.git" -type d -exec git-sync.sh {} \;
