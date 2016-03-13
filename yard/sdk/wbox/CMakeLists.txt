cmake_minimum_required(VERSION 2.8)
project(wbox)


# version
set(WBOX_VERSION_MAJOR 1)
set(WBOX_VERSION_MINOR 0)


# sub dirs
add_subdirectory(src)
add_subdirectory(samples)


# deploy
# build a CPack driven installer package
include (InstallRequiredSystemLibraries)
set (CPACK_RESOURCE_FILE_LICENSE "${CMAKE_CURRENT_SOURCE_DIR}/LICENSE")
set (CPACK_PACKAGE_VERSION_MAJOR "${WBOX_VERSION_MAJOR}")
set (CPACK_PACKAGE_VERSION_MINOR "${WBOX_VERSION_MINOR}")
include (CPack)
