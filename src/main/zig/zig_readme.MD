* Install zig: https://ziglang.org/download/

fast run:
* zig run SortTest.zig
* zig run SortTest.zig < ../../../resources/array_to_sort_75000.txt

test:
* zig test SortTest.zig

build:
* zig build-exe SortTest.zig -target x86_64-windows -O ReleaseFast

run:
* ./SortTest.exe
* ./SortTest.exe < ../../../resources/array_to_sort_75000.txt