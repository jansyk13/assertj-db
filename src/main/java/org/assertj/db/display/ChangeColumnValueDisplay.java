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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.display;

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.navigation.ToValueFromColumn;
import org.assertj.db.type.Value;

/**
 * Display methods for a value of a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnValueDisplay
        extends AbstractDisplayWithValues<ChangeColumnValueDisplay, ChangeColumnDisplay>
        implements ToValueFromColumn<ChangeColumnValueDisplay> {

  /**
   * Constructor.
   *
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value The value on which are the displays.
   */
  public ChangeColumnValueDisplay(ChangeColumnDisplay origin, Value value) {
    super(ChangeColumnValueDisplay.class, origin, value);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtStartPoint() {
    return origin.valueAtStartPoint();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtEndPoint() {
    return origin.valueAtEndPoint();
  }

  /**
   * Returns to level of display methods on a {@link org.assertj.db.type.Column}.
   *
   * @return a object of display methods on a {@link org.assertj.db.type.Column}.
   */
  public ChangeColumnDisplay returnToColumn() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(RepresentationType displayType) {
    return displayType.getValueRepresentation(info, value);
  }
}