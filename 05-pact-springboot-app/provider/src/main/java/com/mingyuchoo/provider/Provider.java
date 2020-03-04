package com.mingyuchoo.provider;

public class Provider {

  private final long id;
  private final String content;

  // Constructor
  public Provider(long id, String content) {
    this.id = id;
    this.content = content;
  }

  // Getter
  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

}
