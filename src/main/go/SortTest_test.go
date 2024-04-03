package main

import (
    "testing"
)

func TestHelloName(t *testing.T) {

    name := "todo"
    name2 := "todo"

    if name != name2 {
        t.Errorf("got %q, wanted %q", name, name2)
    }
}