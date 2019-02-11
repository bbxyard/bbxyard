#!/bin/bash
# markdown to pdf convert
#

function concat() {
  tmp=""
  for x in $1; do tmp="../packages/$x/$2 $tmp"; done;
  echo $tmp
}

function topdf() {
  md2pdf_pf="pandoc -N -s --toc --pdf-engine=xelatex -V CJKmainfont='PingFang SC' -V geometry:margin=1in"
  md2pdf_yh="pandoc -N -s --toc --pdf-engine=xelatex -V CJKmainfont='Microsoft YaHei' -V geometry:margin=1in"
  cmd="${md2pdf_pf} $1 -o $2"
  echo $cmd
  # (. ~/.bash_profile && $cmd)
  # $md2pdf_pf $1 -o "$2"
}


# input/output
PROJ_ROOT_HOME=$(pushd `dirname $0` >/dev/null; pwd; popd >/dev/null);
SUB_DIRS='nbox nbox-core nbox-kit nbox-wepy nbox-res'
DOC_DIR="../docs"
OUT_RS=""

# mid
RS=$(concat "$SUB_DIRS" "README.md")
HS=$(concat "$SUB_DIRS" "HISTORY.md")

function main() {
  ret=$(which pandoc >/dev/null; echo "$?")
  echo "ret=$ret"
  [ "$ret" != "0" ] && echo 'NOT found pandoc tools' && return $ret

  echo 'start MD to PDF convert'
  pushd "$PROJ_ROOT_HOME" >/dev/null
    topdf "$RS" "$DOC_DIR/nbox.pdf"
    topdf "$HS" "$DOC_DIR/nbox-history.pdf"
  popd >/dev/null
  echo 'done'
}

# run
main
