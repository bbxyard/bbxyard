#!/bin/sh

copy_file_with_dir()
{
	dir=$(dirname $1)
	file=$(basename $1)
	dst_dir=$2/$dir

	[ ! -d "$dst_dir" ] && mkdir -p "$dst_dir"
	cp -f "$1" "$dst_dir"
}

# copy file from filelist to /tmp/clone dir.
cat $1 | while read file 
do 
	copy_file_with_dir "$file" /tmp/clone
done

