package com.finance.jwt.config.token.time;

import lombok.Value;

import java.util.Date;

@Value
public class CommonInterval implements Interval {
  Date created = new Date();
  Date expired;

  public CommonInterval(Long expiredInMls) {
    this.expired = new Date(created.getTime() + expiredInMls);
  }

  @Override
  public long inMilliseconds() {
    return expired.getTime() - created.getTime();
  }
}
