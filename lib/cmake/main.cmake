# bx common cmake head
cmake_minimum_required (VERSION 2.8)


# options
macro (check_flags)
    option (debug  "is debug mode?"  OFF)
    option (bits64 "is bits64 mode?" ON)
    # debug or release
    if (debug)
        add_definitions("-g -D_DEBUG")
        message("debug mode")
    else()
        add_definitions("-DNDEBUG -O2")
        message("release mode")
    endif()
    # bits32 or bits64
    if (bits64)
       message ("bits64 mode")
       add_definitions ("-m64")
       set (LIB_DNAME "lib")
       set (BIN_DNAME "bin")
    else ()
       message ("bits32 mode")
       add_definitions ("-m32")
       set (LIB_DNAME "lib32")
       set (BIN_DNAME "bin32")
    endif ()
endmacro()

# compile option
macro (set_common_flags)
    # output
    set (CMAKE_INSTALL_PREFIX "$ENV{HOME}")
    set (ABS_DIST_DIR "${CMAKE_INSTALL_PREFIX}/")
    set (DIST_BIN_DIR "${ABS_DIST_DIR}${BIN_DNAME}")
    set (DIST_LIB_DIR "${ABS_DIST_DIR}${LIB_DNAME}")
    set (CMAKE_LIBRARY_OUTPUT_DIRECTORY lib)
    set (CMAKE_RUNTIME_OUTPUT_DIRECTORY bin)

    # os compile flags
    set (OS_COMM_LIB pthread dl rt)
    add_definitions ("-fpermissive -fPIC")
    add_definitions ("-D_GNU_SOURCE -DLINUX -w -pthread -pipe")
    # add_definitions ("-fshort-wchar -fwide-exec-charset=UTF-16LE")
endmacro()

# FindPkgConfig
macro (add_depends_ev)
    include(FindPkgConfig)
    pkg_check_modules(LIBEVENT REQUIRED libevent)
    include_directories(${LIBEVENT_INCLUDE_DIRS})
    link_directories(${LIBEVENT_LIBRARY_DIRS})
    set(CUR_PROJ_LIB ${CUR_PROJ_LIB} ${LIBEVENT_LIBRARIES})
    message("CUR_PROJ_LIB=${CUR_PROJ_LIB}")
endmacro()

# addexe
macro (addexe name)
    #message("## ARGV=${ARGV} ##")
    #message("## ARGV1=${ARGV1} ##")
    #message("## ARGN=${ARGN} ##")
    add_executable(${name} ${ARGN})
    target_link_libraries(${name} ${CUR_PROJ_LIB} ${OS_COMM_LIB})
    install(TARGETS ${name} DESTINATION ${DIST_BIN_DIR})
endmacro()

# show debug info
macro (show_dbg_info)
    message("PROJECT_NAME=${PROJECT_NAME}")
    message("SRC files=${SRCS}")
    message("CMAKE_INSTALL_PREFIX=${CMAKE_INSTALL_PREFIX}")
    message("CUR_PROJ_LIB=${CUR_PROJ_LIB}")
endmacro()


# common
check_flags()
set_common_flags()
