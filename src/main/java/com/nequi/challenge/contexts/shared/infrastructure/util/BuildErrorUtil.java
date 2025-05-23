package com.nequi.challenge.contexts.shared.infrastructure.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class BuildErrorUtil {
   public static Map<String, String> create(String code, String description) {
      Map<String, String> error = new LinkedHashMap<>();
      error.put(code, description);
      return error;
   }
}
