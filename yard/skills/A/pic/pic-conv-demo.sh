#!/bin/bash
# picture convert demo
#
# install: 
#   [Redhat] sudo yum install -y ImageMagick optipng jpegoptim
#   [Mac] brew install ImageMagick optipng jpegoptim
#
# depends: convert, identify, optipng, jpegoptim
#

# User Var defined
AUTHOR=boxu@yvhai.com
VERSION="0.1.0"
YEAR_BEGIN=2013

# User Var derived
THIS_NAME=$(basename $0)
PROJ_ROOT_HOME=$(pushd `dirname $0` >/dev/null; pwd; popd >/dev/null);
YEAR=$(date +%Y)
COPY_RIGHT="${THIS_NAME} ${VERSION} Copyright (C) ${YEAR_BEGIN}-${YEAR}, ${AUTHOR}"


function init() {
  pushd "$PROJ_ROOT_HOME"
  IN_DIR="./in"
  OUT_DIR="./out"
  [ ! -d "$OUT_DIR" ] && mkdir -p "$OUT_DIR"
  cp -rvf "$IN_DIR"/* "$OUT_DIR/"
  # 公共变量
  srcfile="$OUT_DIR/spt-0001.jpg"
  outbase=${srcfile%.*}
  outext="jpg"
}

function transform() {
  # resize
  convert -resize "375x" "${srcfile}" "${outbase}-resize-375xH.${outext}"
  convert -resize "x300" "${srcfile}" "${outbase}-resize-Wx300.${outext}"
  convert -resize "x300" -gravity center -extent 300x300 "${srcfile}" "${outbase}-resize-300x300.${outext}"
  # crop
  convert -crop 300x300+100+200 "${srcfile}" "${outbase}-crop-300x300+100+200.${outext}"
  # watermark text
  convert -font "Consolas" -fill blue -pointsize 48 -annotate +50+150 "$(date +%Y%m%d-%H:%M:%S)" "${srcfile}" "${outbase}-text-buf-50-150.${outext}"
  convert -fill red -pointsize 48 -annotate +50+50 @copyright.txt "${srcfile}" "${outbase}-text-file-50-50.${outext}"
  # draw -fill none/red
  convert -fill orange -stroke red -strokewidth 3 -draw "rectangle 50,50 100,100" "${srcfile}" "${outbase}-draw-rect.${outext}"
  convert -fill orange -stroke red -strokewidth 3 -draw "line 50,50 100,100" "${srcfile}" "${outbase}-draw-line.${outext}"
  # rotate
  convert -rotate 90 "${srcfile}" "${outbase}-rotate-90.${outext}"
  # flip, flop, transpose, transverse
  convert -flip "${srcfile}" "${outbase}-flip.${outext}"
  convert -flop "${srcfile}" "${outbase}-flop.${outext}"
  convert -transpose "${srcfile}" "${outbase}-transpose.${outext}"
  convert -transverse "${srcfile}" "${outbase}-transverse.${outext}"
}

function compress() {
  # 256色png压缩
  convert -strip -depth 8 -colors 256 "$OUT_DIR/spt-0001.jpg"  "${outbase}-strip-d8-c256.png"
  # optipng -o7 "${outbase}-strip-d8-c256.png"
  # jpeg 压缩 jpegoptim -d <outdir>
  cp "${srcfile}" "${outbase}-jpegoptim-lossless.jpg"
  cp "${srcfile}" "${outbase}-jpegoptim-m85.jpg"
  jpegoptim -p "${outbase}-jpegoptim-lossless.jpg"
  jpegoptim -p -m85 "${outbase}-jpegoptim-m85.jpg"
  jpegoptim -p -d/tmp -m85 "${srcfile}"
  md5sum "${outbase}-jpegoptim-m85.jpg" "/tmp/$(basename $srcfile)"
}

function effect() {
  #特殊效果
  convert -charcoal 2  "${srcfile}" "${outbase}-charcoal-2.jpg" #炭笔 
  convert -colorize 255 "${srcfile}" "${outbase}-colorize-255.jpg" #着色 可以指定三种颜色red/green/blue 
  convert -implode 4 "${srcfile}" "${outbase}-implode-4.jpg" #内爆效果 
  convert -solarize 42 "${srcfile}" "${outbase}-solarize-42.jpg" #曝光，模拟胶片曝光 
  convert -spread 5 "${srcfile}" "${outbase}-spread-5.jpg" #随机移动，参数是位移大小
}

function group() {
  # montage
  montage -bordercolor red -borderwidth 3 -label "%f" -tile 5x3 "$IN_DIR"/*.jpg "$OUT_DIR/montage.jpg"
  # thumbnail
  for img in $(ls $OUT_DIR/*.jpg); do convert -sample 25%x25% $img "${img%.*}-thumb.jpg"; done;
  # gif
  convert -delay 50  "$OUT_DIR/spt-0001.jpg" "$OUT_DIR/spt-0002.jpg" "$OUT_DIR/spt-0005.jpg" "${outbase}-delay-50x3.gif"
  convert -delay 100 "$OUT_DIR/spt-0010.jpg" "$OUT_DIR/spt-0030.jpg" "$OUT_DIR/spt-0040.jpg" "${outbase}-delay-100x2.gif"
  convert "${outbase}-delay-50x3.gif" "${outbase}-delay-100x2.gif" "${outbase}-delay-50x3-100x2.gif"
  # append
  convert "$OUT_DIR/spt-0001.jpg" "$OUT_DIR/spt-0002.jpg" "$OUT_DIR/spt-0005.jpg" +append "${outbase}+append.${outext}"
  convert "$OUT_DIR/spt-0001.jpg" "$OUT_DIR/spt-0002.jpg" "$OUT_DIR/spt-0005.jpg" -append "${outbase}-append.${outext}"
  convert \( "$OUT_DIR/spt-0001.jpg" "$OUT_DIR/spt-0002.jpg" "$OUT_DIR/spt-0001.jpg" +append \) \
    \( "$OUT_DIR/spt-0002.jpg" "$OUT_DIR/spt-0005.jpg" "$OUT_DIR/spt-0002.jpg" +append \) \
    \( "$OUT_DIR/spt-0005.jpg" "$OUT_DIR/spt-0010.jpg" "$OUT_DIR/spt-0005.jpg" +append \) \
    -append "${outbase}+appendx3-append.${outext}"
}

function all() {
  transform "$@"
  compress "$@"
  effect "$@"
  group "$@"
}

function showUsage() {
cat << EOF
${COPY_RIGHT}
Usage: ${THIS_NAME} <cmd> [OPTION]

Options:

  -v, --version                             output the version number
  -h, --help                                output usage information

Commands:

  bar [options] <arg1> <arg2>               demo-bar
  dir-index|di [options] <inDir> <outFile>  目录索引化

Known values for OPTIONS are:
  --prefix[=DIR]    change prefix to DIR
  --bindir          print location where binaries are installed
  --includes        print include information
  --includedir      print location where headers are installed
  --ldflags
  --libs            print library information
  --outfile -o      output file  
  ...
  --help            print this help


EOF
}

RETVAL=0
function main() {
  if [ $# -eq 0 ]; then
    showUsage
    return 1
  fi

  init
  sub="$1"
  shift
  case "$sub" in
    transform) transform "$@" ;;
    compress) compress "$@" ;;
    effect) effect "$@" ;;
    group) group "$@" ;;
    all) all "$@" ;;
    *) showUsage ;;
  esac

  return $RETVAL
}

main "$@"
