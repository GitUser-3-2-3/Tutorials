package main

import "net/http"

type Handler interface {
	ServeHttp(http.ResponseWriter, *http.Request)
}
type HandlerFunc func(http.ResponseWriter, *http.Request)

func (handlerFunc HandlerFunc) ServeHttp(writer http.ResponseWriter, req *http.Request) {
	handlerFunc(writer, req)
}
