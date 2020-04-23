/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.tools.build.bundletool.device;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/** Parses the output of the "pm list packages" ADB shell command. */
public final class PackagesParser {

  /** Parses output of "pm list packages". */
  public ImmutableSet<String> parse(ImmutableList<String> listPackagesOutput) {
    // The command lists the packages in the form
    // package:com.google.a
    // package:com.google.b
    // ...
    return listPackagesOutput.stream()
        .filter(packageLine -> packageLine.contains("package:"))
        .map(packageLine -> packageLine.replace("package:", "").trim())
        .collect(toImmutableSet());
  }
}
