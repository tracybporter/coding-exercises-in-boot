package com.phg.codingexercises

import groovyx.net.http.RESTClient

class SutRestClient {
  @Delegate
  RESTClient restClient

  SutRestClient() {
    restClient = new RESTClient('http://localhost:8080')
    restClient.handler.failure = restClient.handler.success
  }
}
