cmake_minimum_required(VERSION 2.8)
#project(grammar)


function(show_buildin_var)
    message(STATUS "PROJECT_NAME=${PROJECT_NAME}")
endfunction()

function(show_os_type)
    message(STATUS "cmake主版本号~CMAKE_MAJOR_VERSION=${CMAKE_MAJOR_VERSION}") # 2.8.6~2
    message(STATUS "cmake次版本号~CMAKE_MINOR_VERSION=${CMAKE_MINOR_VERSION}") # 2.8.6~8
    message(STATUS "cmake补丁等级~CMAKE_PATCH_VERSION=${CMAKE_PATCH_VERSION}") # 2.8.6~6
    message(STATUS "系统名称~CMAKE_SYSTEM=${CMAKE_SYSTEM}") # Linux-2.6.22
    message(STATUS "不包含版本的系统名~CMAKE_SYSTEM_NAME=${CMAKE_SYSTEM_NAME}") # Linux
    message(STATUS "系统版本~CMAKE_SYSTEM_VERSION=${CMAKE_SYSTEM_VERSION}") # 2.6.22
    message(STATUS "处理器名称~CMAKE_SYSTEM_PROCESSOR=${CMAKE_SYSTEM_PROCESSOR}") # x86_64
    if (APPLE)     #
        message(STATUS "CURRENT OS IS: APPLE")
    elseif (UNIX)  # 在所有的类UNIX平台为TRUE,包括OS X和cygwin
        message(STATUS "CURRENT OS IS: UNIX")
    elseif (WIN32) # 在所有的win32平台为TRUE,包括cygwin
        message(STATUS "CURRENT OS IS: Windows")
    endif()
endfunction()

macro(show_macro_buildin)
    message(STATUS "## ARGV=${ARGV} ##")
    message(STATUS "## ARGV1=${ARGV1} ##")
    message(STATUS "## ARGN=${ARGN} ##")
endmacro()

show_buildin_var()
show_os_type()
