# bx common cmake head
cmake_minimum_required (VERSION 2.8)

# common lib
include(FindPkgConfig)

# test SDK_HOME
message(STATUS "##USER_HOME##=$ENV{HOME}")
message(STATUS "##CMAKE_INSTALL_PREFIX##=${CMAKE_INSTALL_PREFIX}")
set (SLN_DIR "${CMAKE_CURRENT_LIST_DIR}/../..")

# add sdk_depends
macro (add_common_sdk_path)
    set(USER_SDK_HOME "$ENV{SDK_HOME}")
    message(STATUS "##USER_SDK_HOME##=$ENV{USER_SDK_HOME}")
    include_directories(${USER_SDK_HOME}/include)
    link_directories(${USER_SDK_HOME}/lib)
endmacro()

# options
macro (check_flags)
    option (debug  "is debug mode?"  OFF)
    option (bits64 "is bits64 mode?" ON)
    # debug or release
    if (debug)
        add_definitions("-g -D_DEBUG")
        message(STATUS "###debug mode###")
    else()
        add_definitions("-DNDEBUG -O2")
        message(STATUS "###release mode###")
    endif()
    # bits32 or bits64
    if (bits64)
       message (STATUS "###bits64 mode###")
       add_definitions ("-m64")
       set (LIB_DNAME "lib")
       set (BIN_DNAME "bin")
    else ()
       message (STATUS "###bits32 mode###")
       add_definitions ("-m32")
       set (LIB_DNAME "lib32")
       set (BIN_DNAME "bin32")
    endif ()
    set (INC_DNAME "include")
endmacro()

# compile option
macro (set_common_flags)
    # output
    if (CMAKE_INSTALL_PREFIX STREQUAL "/usr/local")
        set (CMAKE_INSTALL_PREFIX "${SLN_DIR}")
        message(STATUS "==USER DEFINE#CMAKE_INSTALL_PREFIX=${CMAKE_INSTALL_PREFIX}")
    endif()
    set (CMAKE_LIBRARY_OUTPUT_DIRECTORY lib)
    set (CMAKE_RUNTIME_OUTPUT_DIRECTORY bin)

    # os compile flags
    if (APPLE)
        set (OS_COMM_LIB pthread dl)
    else()
        set (OS_COMM_LIB pthread dl rt)
    endif()
    add_definitions ("-fpermissive -fPIC")
    add_definitions ("-D_GNU_SOURCE -DLINUX -w -pthread -pipe")
    # add_definitions ("-fshort-wchar -fwide-exec-charset=UTF-16LE")
endmacro()

# FindPkgConfig
macro (add_depends_apr)
    pkg_check_modules(APR REQUIRED apr-1)
    include_directories(${APR_INCLUDE_DIRS})
    link_directories(${APR_LIBRARY_DIRS})
    set(CUR_PROJ_LIB ${CUR_PROJ_LIB} ${APR_LIBRARIES})
    message(STATUS "###CUR_PROJ_LIB###=${CUR_PROJ_LIB}")
endmacro()
macro (add_depends_apu)
    pkg_check_modules(APU REQUIRED apr-util-1)
    include_directories(${APU_INCLUDE_DIRS})
    link_directories(${APU_LIBRARY_DIRS})
    set(CUR_PROJ_LIB ${CUR_PROJ_LIB} ${APU_LIBRARIES})
    message(STATUS "###CUR_PROJ_LIB###=${CUR_PROJ_LIB}")
endmacro()

macro (add_depends_ev)
    pkg_check_modules(LIBEVENT REQUIRED libevent)
    include_directories(${LIBEVENT_INCLUDE_DIRS})
    link_directories(${LIBEVENT_LIBRARY_DIRS})
    set(CUR_PROJ_LIB ${CUR_PROJ_LIB} ${LIBEVENT_LIBRARIES})
    message(STATUS "###CUR_PROJ_LIB###=${CUR_PROJ_LIB}")
endmacro()
macro (add_depends_evhtp)
    add_depends_ev()
    set(CUR_PROJ_LIB evhtp ${CUR_PROJ_LIB})
    message(STATUS "###CUR_PROJ_LIB###=${CUR_PROJ_LIB}")
endmacro()

macro (add_depends_gtest)
    set(CUR_PROJ_LIB ${CUR_PROJ_LIB} gtest)
    message(STATUS "###CUR_PROJ_LIB###=${CUR_PROJ_LIB}")
endmacro()
macro (add_depends_gtest_with_main)
    set(CUR_PROJ_LIB ${CUR_PROJ_LIB} gtest gtest_main)
    message(STATUS "###CUR_PROJ_LIB###=${CUR_PROJ_LIB}")
endmacro()

macro (add_depends_protobuf)
    set(CUR_PROJ_LIB ${CUR_PROJ_LIB} protobuf)
    message(STATUS "###CUR_PROJ_LIB###=${CUR_PROJ_LIB}")
endmacro()

# addexe
macro (addexe name)
    add_executable(${name} ${ARGN})
    target_link_libraries(${name} ${CUR_PROJ_LIB} ${OS_COMM_LIB})
    install(TARGETS ${name} DESTINATION ${BIN_DNAME})
endmacro()

# addso - dynamic so
macro (addso name)
    add_library(${name} SHARED ${ARGN})
    target_link_libraries(${name} ${CUR_PROJ_LIB} ${OS_COMM_LIB})
    install(TARGETS ${name} DESTINATION ${LIB_DNAME})
endmacro()

# addlib - static lib
macro (addlib name)
    add_library(${name} STATIC ${ARGN})
    install(TARGETS ${name} DESTINATION ${LIB_DNAME})
endmacro()

# add - head-file
macro (addinc file)
    install (FILES ${file} DESTINATION ${INC_DNAME})
endmacro()

# show debug info
macro (show_dbg_info)
    message(STATUS "##PROJECT_NAME##=${PROJECT_NAME}")
    message(STATUS "##SRC files##=${SRCS}")
    message(STATUS "##CUR_PROJ_LIB##=${CUR_PROJ_LIB}")
    message(STATUS "##CMAKE_INSTALL_PREFIX##=${CMAKE_INSTALL_PREFIX}")
    message(STATUS "##DIST_BIN_DIR##=${CMAKE_INSTALL_PREFIX}/${BIN_DNAME}")
    message(STATUS "##DIST_LIB_DIR##=${CMAKE_INSTALL_PREFIX}/${LIB_DNAME}")
    message(STATUS "##DIST_INC_DIR##=${CMAKE_INSTALL_PREFIX}/${INC_DNAME}")
endmacro()


# common
check_flags()
set_common_flags()
# include and lib path
add_common_sdk_path()
