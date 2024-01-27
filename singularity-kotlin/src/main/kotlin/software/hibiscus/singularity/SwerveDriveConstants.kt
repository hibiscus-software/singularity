/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity

import edu.wpi.first.units.Distance
import edu.wpi.first.units.Measure
import software.hibiscus.singularity.imu.NavXPort
import software.hibiscus.singularity.imu.NavXPortType

final class SwerveDriveConstants(
    imuId: Int,
    wheelBaseMeters: Measure<Distance>,
    trackWidthMeters: Measure<Distance>
) {
  var imuId: Int

  var wheelBaseMeters: Measure<Distance>
  var trackWidthMeters: Measure<Distance>

  lateinit var navXPortType: NavXPortType
  lateinit var navXPort: NavXPort

  init {
    this.imuId = imuId

    this.wheelBaseMeters = wheelBaseMeters
    this.trackWidthMeters = trackWidthMeters
  }

  constructor(
      imuId: Int,
      wheelBaseMeters: Measure<Distance>,
      trackWidthMeters: Measure<Distance>,
      navXPortType: NavXPortType,
      navXPort: NavXPort
  ) : this(imuId, wheelBaseMeters, trackWidthMeters) {
    this.navXPortType = navXPortType
    this.navXPort = navXPort
  }
}
