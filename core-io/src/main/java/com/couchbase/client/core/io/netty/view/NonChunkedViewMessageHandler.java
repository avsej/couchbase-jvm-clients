/*
 * Copyright (c) 2018 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.couchbase.client.core.io.netty.view;

import com.couchbase.client.core.endpoint.BaseEndpoint;
import com.couchbase.client.core.error.ViewServiceException;
import com.couchbase.client.core.io.netty.NonChunkedHttpMessageHandler;
import com.couchbase.client.core.service.ServiceType;

import java.nio.charset.StandardCharsets;

class NonChunkedViewMessageHandler extends NonChunkedHttpMessageHandler {

  NonChunkedViewMessageHandler(BaseEndpoint endpoint) {
    super(endpoint, ServiceType.VIEWS);
  }

  @Override
  protected Exception failRequestWith(final String content) {
    return new ViewServiceException(content.getBytes(StandardCharsets.UTF_8));
  }

}
