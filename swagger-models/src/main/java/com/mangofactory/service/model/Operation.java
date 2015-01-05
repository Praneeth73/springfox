package com.mangofactory.service.model;

import com.google.common.base.Function;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;

public class Operation {
  private final String method;
  private final String summary;
  private final String notes;
  private final String responseClass;
  private final String dataType;
  private final String nickname;
  private final int position;
  private final List<String> produces;
  private final List<String> consumes;
  private final List<String> protocol;
  private final Map<String, List<AuthorizationScope>> authorizations;
  private final List<Parameter> parameters;
  private final Set<ResponseMessage> responseMessages;
  private final String deprecated;

  public Operation(String method, String summary, String notes, String responseClass, String nickname, int position,
                   List<String> produces, List<String> consumes, List<String> protocol,
                   List<Authorization>
                           authorizations, List<Parameter> parameters, Set<ResponseMessage> responseMessages, String
                           deprecated) {
    this.method = method;
    this.summary = summary;
    this.notes = notes;
    this.responseClass = responseClass;
    this.dataType = responseClass;
    this.nickname = nickname;
    this.position = position;
    this.produces = produces;
    this.consumes = consumes;
    this.protocol = protocol;
    this.authorizations = toAuthorizationsMap(authorizations);
    this.parameters = parameters;
    this.responseMessages = responseMessages;
    this.deprecated = deprecated;
  }

  private Map<String, List<AuthorizationScope>> toAuthorizationsMap(List<Authorization> authorizations) {
    return transformEntries(uniqueIndex(authorizations, byType()), toScopes());
  }

  private EntryTransformer<? super String, ? super Authorization, List<AuthorizationScope>> toScopes() {
    return new EntryTransformer<String, Authorization, List<AuthorizationScope>>() {
      @Override
      public List<AuthorizationScope> transformEntry(String key, Authorization value) {
        return newArrayList(value.getScopes());
      }
    };
  }

  private Function<? super Authorization, String> byType() {
    return new Function<Authorization, String>() {
      @Override
      public String apply(Authorization input) {
        return input.getType();
      }
    };
  }

  public String getDataType() {
    return dataType;
  }

  public String getMethod() {
    return method;
  }

  public String getSummary() {
    return summary;
  }

  public String getNotes() {
    return notes;
  }

  public String getResponseClass() {
    return responseClass;
  }

  public String getNickname() {
    return nickname;
  }

  public int getPosition() {
    return position;
  }

  public List<String> getProduces() {
    return produces;
  }

  public List<String> getConsumes() {
    return consumes;
  }

  public List<String> getProtocol() {
    return protocol;
  }

  public Map<String, List<AuthorizationScope>> getAuthorizations() {
    return authorizations;
  }

  public List<Parameter> getParameters() {
    return parameters;
  }

  public Set<ResponseMessage> getResponseMessages() {
    return responseMessages;
  }

  public String getDeprecated() {
    return deprecated;
  }
}
