cmake_minimum_required(VERSION 2.8)
project(cbox-guide)


# sub dirs
add_subdirectory(src/main)
#add_subdirectory(src/samples)
#add_subdirectory(src/test)


# include common cmake
include(../../../lib/cmake/main.cmake)


# add depends, flags and show debug info
#add_depends_ev()
#show_dbg_info()

# gen and deploy final apps
#aux_source_directory(. SRCS)
#addexe(${PROJECT_NAME} ${SRCS})
