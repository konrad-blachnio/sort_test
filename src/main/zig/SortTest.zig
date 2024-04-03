const expect = @import("std").testing.expect;
const std = @import("std");


pub fn main() !void {

    var start: i64 = std.time.milliTimestamp();
    const size: u32 = try read_u32();
    const allocator = std.heap.page_allocator;
    const arrayToSort: []i32 = try allocator.alloc(i32, size);
    errdefer allocator.free(arrayToSort);
    try readArray(@intCast(size), arrayToSort);

    var finish: i64 = std.time.milliTimestamp();
    printLog(start, finish, "Reading input array of size:");


    const cloneArrayToStdSort: []i32 = try allocator.alloc(i32, size);
    errdefer allocator.free(cloneArrayToStdSort);
    @memcpy(cloneArrayToStdSort, arrayToSort);

    start = std.time.milliTimestamp();
    stdSort(cloneArrayToStdSort);
    finish = std.time.milliTimestamp();
    printLog(start, finish, "std.sort of size: ");

    const cloneArrayToInsertionSort: []i32 = try allocator.alloc(i32, size);
    errdefer allocator.free(cloneArrayToInsertionSort);
    @memcpy(cloneArrayToInsertionSort, arrayToSort);

    start = std.time.milliTimestamp();
    insertionSort(cloneArrayToInsertionSort);
    finish = std.time.milliTimestamp();
    printLog(start, finish, "insertionSort of size: ");
}

fn printLog(start: i64, finish: i64, message: []const u8) void {
    const testTime: i64 = finish - start;
    std.debug.print("{s} took: {} milis, {} sec \n", .{message, testTime,  @divFloor(testTime, 1000)});
}

fn stdSort(array: []i32) void {
    std.mem.sort(i32, array, {}, comptime std.sort.asc(i32));
}

fn insertionSort(array: []i32) void {
    var min: i32 = undefined; var min_index: u32 = undefined;
    for (0..array.len - 1) |i| {
        min = array[i];
        min_index = @intCast(i);
        for (i + 1..array.len) |j| {
            if (array[j] < min) {
                min = array[j];
                min_index = @intCast(j);
            }
        }
        swap(array, @intCast(i), min_index);
    }
}

fn swap(array: []i32, firstIndex: u32, secondIndex: u32) void {
    const temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
}

fn getArraySize() !u32 {
    return try read_u32();
}

fn readArray(size: u32, array: []i32) !void {
    const stdin = std.io.getStdIn();
    for (0..size) |i| {
        array[i] = try std_read_i32(&stdin);
    }
    //printArrayStart(array, 13);
}

fn printArray(array: []i32) void {
    std.debug.print("Array[{}]: {any}\n", .{array.len, array});
}

fn printArrayStart(array: []i32, startElems: u32) void {
    std.debug.print("Array[{}]: {any}\n", .{array.len, array[0..startElems]});
}

fn read_u32() !u32 {
    const stdin = std.io.getStdIn();
    return try std_read_u32(&stdin);
}

fn std_read_u32(stdin: *const @TypeOf(std.io.getStdIn())) !u32 {
    var input: [10]u8 = undefined;
    const reader = stdin.reader();
    const read = try reader.readUntilDelimiter(&input, '\n');
    //std.debug.print("read: '{s}'\n", .{read});
    const val = try std.fmt.parseUnsigned(u32, read[0..read.len-1], 10);
    //std.debug.print("val: '{}'\n", .{val});
    return val;
}

fn std_read_i32(stdin: *const @TypeOf(std.io.getStdIn())) !i32 {
    var input: [10]u8 = undefined;
    const reader = stdin.reader();
    const read = try reader.readUntilDelimiter(&input, '\n');
    const val = try std.fmt.parseInt(i32, read[0..read.len-1], 10);
    return val;
}


test "insertionSort test" {
    var arrayToSort = [_]i32{6,2,4,3,5,1};
    const sortedArray = [_]i32{1,2,3,4,5,6};
    insertionSort(&arrayToSort);

    for (0..arrayToSort.len) |i| {
        try expect(arrayToSort[i] ==  sortedArray[i]);
    }

    //printArray(&arrayToSort);
}