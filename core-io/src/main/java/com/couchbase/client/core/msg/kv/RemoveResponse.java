package com.couchbase.client.core.msg.kv;

import com.couchbase.client.core.msg.BaseResponse;
import com.couchbase.client.core.msg.ResponseStatus;

public class RemoveResponse extends BaseResponse {

  RemoveResponse(ResponseStatus status) {
    super(status);
  }
}
