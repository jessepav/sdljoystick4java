cmake_minimum_required(VERSION 3.21)

project(sdljoystick4java
    LANGUAGES CXX
)

option(SDLJOYSTICK4JAVA_USE_PCH "Use precompiled headers" FALSE)

find_package(JNI REQUIRED)
set(SDL_BUILDING_LIBRARY)
find_package(SDL REQUIRED)

add_library(sdljoystick4java
    MODULE
    "src/sdljoystick4java.cpp"
)

target_include_directories(sdljoystick4java PRIVATE "${CMAKE_CURRENT_SOURCE_DIR}" "${JNI_INCLUDE_DIRS}")
target_link_libraries(sdljoystick4java PRIVATE SDL::SDL)

if (SDLJOYSTICK4JAVA_USE_PCH)
    target_precompile_headers(sdljoystick4java PRIVATE src/sdljoystick4java.h)
endif()
