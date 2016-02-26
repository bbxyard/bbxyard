cmake_minimum_required(VERSION 2.8)
project(http-request)

# define
set(SLNDIR .)
aux_source_directory(. SRCS)
set(CMAKE_INSTALL_PREFIX $ENV{HOME}/bin)

# find depends
include(FindPkgConfig)
pkg_check_modules(LIBEVENT REQUIRED libevent)

# include common cmake
#include(${SLNDIR}/...)

# show debug info
message("SRC files=${SRCS}  # CMAKE_INSTALL_PREFIX=${CMAKE_INSTALL_PREFIX}")

# add libevent deps
include_directories(${LIBEVENT_INCLUDE_DIRS})
link_directories(${LIBEVENT_LIBRARY_DIRS})

# combine libs
set(CUR_PROJ_LIB ${LIBEVENT_LIBRARIES})

# final
add_executable(${PROJECT_NAME} ${SRCS})
target_link_libraries(${PROJECT_NAME} ${CUR_PROJ_LIB})
install(TARGETS ${PROJECT_NAME} DESTINATION ${CMAKE_INSTALL_PREFIX})

