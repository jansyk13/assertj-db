/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.text.ParseException;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on the methods which verifies if a value is after a time value.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_IsAfterOrEqualTo_TimeValue_Test extends AbstractTest {

  /**
   * This method tests that the value is after or equal to a time.
   * @throws java.text.ParseException
   */
  @Test
  @NeedReload
  public void test_if_value_is_after_or_equal_to_time() throws ParseException {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForOtherTests();
    changes.setEndPointNow();

    assertThat(changes).change().rowAtEndPoint().value("var8")
            .isAfterOrEqualTo(TimeValue.of(9, 46, 29))
            .change().rowAtEndPoint().value("var8")
            .isAfterOrEqualTo(TimeValue.parse("12:29:48"))
            .change(0).rowAtEndPoint().value("var8")
            .isAfterOrEqualTo(TimeValue.of(9, 46, 30))
            .change().rowAtEndPoint().value("var8")
            .isAfterOrEqualTo(TimeValue.parse("12:29:49"));
  }

  /**
   * This method should fail because the value is not after or equal to the time value.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_after_or_equal_to() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var8")
                       .isAfterOrEqualTo(TimeValue.of(9, 46, 31));

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      org.assertj.core.api.Assertions.assertThat(e.getLocalizedMessage()).isEqualTo("[Value at index 7 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <09:46:30.000000000>\n" +
                                                                                    "to be after or equal to \n" +
                                                                                    "  <09:46:31.000000000>");
    }
  }

  /**
   * This method should fail because the value is not a time.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_is_not_a_date() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForOtherTests();
      changes.setEndPointNow();

      assertThat(changes).change().rowAtEndPoint().value("var1")
                       .as("var1").isAfterOrEqualTo(TimeValue.of(9, 46, 29));

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      org.assertj.core.api.Assertions.assertThat(e.getLocalizedMessage()).isEqualTo("[var1] \n" +
                                                                                    "Expecting:\n" +
                                                                                    "  <1>\n" +
                                                                                    "to be of type\n" +
                                                                                    "  <TIME>\n" +
                                                                                    "but was of type\n" +
                                                                                    "  <NUMBER>");
    }
  }

}