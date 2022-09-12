package com.group.libraryapp.dto.user.request;

import org.jetbrains.annotations.NotNull;

public class UserUpdateRequest {

  private long id;
  private String name;

  public UserUpdateRequest(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  @NotNull
  public String getName() {
    return name;
  }

}
