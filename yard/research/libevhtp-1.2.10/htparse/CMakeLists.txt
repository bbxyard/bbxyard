cmake_minimum_required(VERSION 2.8)
project(evhtp-test)


# define
set(SLNDIR .)
set(PREFIX "evhtp_")
aux_source_directory(. SRCS)

# include common cmake
include(${SLNDIR}/../../../../lib/cmake/main.cmake)


# add depends, flags and show debug info
add_depends_evhtp()
show_dbg_info()

# gen and deploy final apps
addexe("${PREFIX}htparse_test" "test.c")
