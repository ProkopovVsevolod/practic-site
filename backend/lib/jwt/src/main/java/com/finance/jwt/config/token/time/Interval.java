package com.finance.jwt.config.token.time;

import java.util.Date;

public interface Interval {
  Date getCreated();
  Date getExpired();
  long inMilliseconds();
}
