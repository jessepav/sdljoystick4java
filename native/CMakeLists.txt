cmake_minimum_required(VERSION 3.21)

project(sdljoystick4java
    LANGUAGES CXX
)

set(SDLJOYSTICK4JAVA_JDK_HOME "" CACHE PATH "Path to JDK home directory")
set(SDLJOYSTICK4JAVA_SDL_DIR "" CACHE PATH "Path to SDL directory")

if(NOT SDLJOYSTICK4JAVA_JDK_HOME)
    if(DEFINED ENV{JDK_HOME})
        set(SDLJOYSTICK4JAVA_JDK_HOME "$ENV{JDK_HOME}")
    elseif(DEFINED ENV{IDEA_JDK})
        set(SDLJOYSTICK4JAVA_JDK_HOME "$ENV{IDEA_JDK}")
    else()
        message(FATAL_ERROR "SDLJOYSTICK4JAVA_JDK_HOME not set")
    endif()
endif()

if (NOT SDLJOYSTICK4JAVA_SDL_DIR)
    if (DEFINED ENV{SDL_DIR})
        set(SDLJOYSTICK4JAVA_SDL_DIR "$ENV{SDL_DIR}")
    else()
        message(FATAL_ERROR "SDLJOYSTICK4JAVA_SDL_DIR not set")
    endif()
endif()

message("Using JDK at ${SDLJOYSTICK4JAVA_JDK_HOME}")
message("Using SDL at ${SDLJOYSTICK4JAVA_SDL_DIR}")

add_library(sdljoystick4java
    MODULE
    "src/sdljoystick4java.cpp"
)

if (CMAKE_SIZEOF_VOID_P EQUAL 4)
    set(BITS32 TRUE)
    set(BITS64 FALSE)
    message("Configuring 32-bit build.")
elseif(CMAKE_SIZEOF_VOID_P EQUAL 8)
    set(BITS32 FALSE)
    set(BITS64 TRUE)
    message("Configuring 64-bit build.")
else()
    message(FATAL_ERROR "Unknown architecture bits (expected 32 or 64)!")
endif()

target_include_directories(sdljoystick4java PRIVATE "${CMAKE_SOURCE_DIR}")
target_include_directories(sdljoystick4java PRIVATE "${SDLJOYSTICK4JAVA_JDK_HOME}/include")
target_include_directories(sdljoystick4java PRIVATE "${SDLJOYSTICK4JAVA_SDL_DIR}/include")

if (WIN32)
    target_include_directories(sdljoystick4java PRIVATE "${SDLJOYSTICK4JAVA_JDK_HOME}/include/win32")
    if (BITS32)
        target_link_libraries(sdljoystick4java PRIVATE "${SDLJOYSTICK4JAVA_SDL_DIR}/lib/x86/SDL2.lib")
    else()
        target_link_libraries(sdljoystick4java PRIVATE "${SDLJOYSTICK4JAVA_SDL_DIR}/lib/x64/SDL2.lib")
    endif()
else()
    message(FATAL_ERROR "Support for non-win32 platforms not yet implemented")
endif()
