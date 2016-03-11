cmake_minimum_required(VERSION 2.8)
project(libevent_demo)


# sub dirs
add_subdirectory(org-sample)
add_subdirectory(libevent-thread)


# include common cmake
include(../../../lib/cmake/main.cmake)

# add depends, flags and show debug info
add_depends_ev()
show_dbg_info()

# gen and deploy final apps
aux_source_directory(. SRCS)
#addexe(${PROJECT_NAME} ${SRCS})
addexe(http-request http-request.cpp)
addexe(ev-mt-httpd ev-mt-httpd.cpp)
