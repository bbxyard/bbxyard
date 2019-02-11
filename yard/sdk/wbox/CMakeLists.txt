cmake_minimum_required(VERSION 2.8)
project(wbox)


# version
set(WBOX_VERSION_MAJOR 1)
set(WBOX_VERSION_MINOR 0)
set(WBOX_VERSION_PATCH 2)

# configure
configure_file("${PROJECT_SOURCE_DIR}/wbox.in.h" "${PROJECT_BINARY_DIR}/wbox.h")

include(../../../lib/cmake/main.cmake)
addinc("${PROJECT_BINARY_DIR}/wbox.h")

# sub dirs
add_subdirectory(src)
add_subdirectory(samples)


# deploy
# build a CPack driven installer package
include(InstallRequiredSystemLibraries)
set(CPACK_RESOURCE_FILE_LICENSE "${CMAKE_CURRENT_SOURCE_DIR}/LICENSE")
set(CPACK_PACKAGE_VERSION_MAJOR "${WBOX_VERSION_MAJOR}")
set(CPACK_PACKAGE_VERSION_MINOR "${WBOX_VERSION_MINOR}")
set(CPACK_PACKAGE_VERSION_PATCH "${WBOX_VERSION_PATCH}")
set(CPACK_PACKAGE_FILE_NAME "${PROJECT_NAME}-${WBOX_VERSION_MAJOR}.${WBOX_VERSION_MINOR}.${WBOX_VERSION_PATCH}")
SET(CPACK_SOURCE_PACKAGE_FILE_NAME "${PROJECT_NAME}-${WBOX_VERSION_MAJOR}.${WBOX_VERSION_MINOR}.${WBOX_VERSION_PATCH}-src")
set(CPACK_BINARY_TBZ2 ON)
set(CPACK_SOURCE_IGNORE_FILES "^${PROJECT_SOURCE_DIR}/build*/")
include(CPack)
