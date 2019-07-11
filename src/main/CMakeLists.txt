cmake_minimum_required(VERSION 2.8)
project(cbox)


# include common cmake
include(../../../../../lib/cmake/main.cmake)
include_directories(../../include)

# src files
aux_source_directory(. SRCS)

# add depends, flags and show debug info
# add_depends_ev()
show_dbg_info()

# gen and deploy final apps
addlib(${PROJECT_NAME} ${SRCS})
