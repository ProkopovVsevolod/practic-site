package com.finance.budget.view.mapper;

import com.finance.budget.domain.CompositeId;
import com.finance.budget.view.dto.CompositeIdDto;

public class IdMapper {
  public CompositeId convert(CompositeIdDto compositeIdDto) {
    return new CompositeId(
      compositeIdDto.getId(),
      compositeIdDto.getUserId()
    );
  }

  public CompositeIdDto convert(CompositeId compositeId) {
    return new CompositeIdDto(
      compositeId.getId(),
      compositeId.getUserId()
    );
  }
}
