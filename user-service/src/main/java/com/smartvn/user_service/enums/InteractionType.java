package com.smartvn.user_service.enums;

import lombok.Getter;

@Getter
public enum InteractionType {
  PURCHASE("PURCHASE", 3),
  ADD_TO_CART("ADD_TO_CART", 2),
  CLICK("CLICK", 1);

  private String value;
  private int weight;

  private InteractionType(String v, int w) {
    this.value = v;
    this.weight = w;
  }
}
