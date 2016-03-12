cmake_minimum_required(VERSION 2.8)
project(libevent_demo)


# sub dirs
add_subdirectory(org-sample)
add_subdirectory(libevent-thread)
add_subdirectory(test)


# include common cmake
include(../../../lib/cmake/main.cmake)

# add depends, flags and show debug info
add_depends_ev()
show_dbg_info()

# gen and deploy final apps
aux_source_directory(. SRCS)
#addexe(${PROJECT_NAME} ${SRCS})
addexe(http-request http-request.cpp)
addso(wbox ev-mt-httpd.cpp)
addexe(wbox-test wbox-test.cpp)
