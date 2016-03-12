cmake_minimum_required(VERSION 2.8)
project(org-sample)


# define
set(SLNDIR .)
aux_source_directory(. SRCS)

# include common cmake
include(${SLNDIR}/../../../../lib/cmake/main.cmake)


# add depends, flags and show debug info
add_depends_ev()
show_dbg_info()

# gen and deploy final apps
include_directories(..)
addso(wbox ${SRCS})
