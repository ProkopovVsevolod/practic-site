package com.finance.budget.domain;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CompositeSequenceGeneratorPostgres implements IdentifierGenerator {
  private final Map<String, Boolean> sequenceAlreadyGenerated = new ConcurrentHashMap<>();
  @Override
  public CompositeId generate(SharedSessionContractImplementor session, Object object) {
    DependentByUserEntity entity = (DependentByUserEntity) object;
    String seqName = seqNameGenerate(entity);

    lazyCreateSequence(session, seqName);
    Long nextId = nextId(session, seqName);

    CompositeId compositeId = entity.getCompositeId();
    compositeId.setId(nextId);
    return compositeId;
  }

  private void lazyCreateSequence(SharedSessionContractImplementor session, String seqName) {
    if (!sequenceAlreadyGenerated.containsKey(seqName)) {
      String sqlCreateSequence = "CREATE SEQUENCE if not exists " + seqName + " START 1 INCREMENT BY 1";
      session.createNativeQuery(sqlCreateSequence, Integer.class)
        .executeUpdate();
      sequenceAlreadyGenerated.put(seqName, true);
    }
  }

  private Long nextId(SharedSessionContractImplementor session, String seqName) {
    String sqlNextSequenceValue = "SELECT nextval('" + seqName + "')";
    return session.createNativeQuery(sqlNextSequenceValue, Long.class)
      .getSingleResult();
  }

  private String seqNameGenerate(DependentByUserEntity entity) {
    String simpleName = entity.getClass().getSimpleName();
    return simpleName.toLowerCase() + "_seq";
  }
}
