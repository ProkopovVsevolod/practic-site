package com.finance.jwt.domain.token.time;

import java.util.Date;

public interface Interval {
  Date getCreated();
  Date getExpired();
  long inMilliseconds();
}
