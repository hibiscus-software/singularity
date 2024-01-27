/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.encoder

import com.ctre.phoenix6.configs.CANcoderConfiguration
import com.ctre.phoenix6.hardware.CANcoder
import com.ctre.phoenix6.signals.SensorDirectionValue

class CANCoderSwerveEncoder(encoder: CANcoder) : SwerveEncoder() {
  private var encoder: CANcoder
  private var canCoderConfig: CANcoderConfiguration = CANcoderConfiguration()

  private var hasConfigChanged: Boolean = false

  init {
    this.encoder = encoder
  }

  override fun getPosition(): Double {
    return encoder.getPosition().valueAsDouble
  }

  override fun getVelocity(): Double {
    return encoder.getVelocity().valueAsDouble
  }

  override fun setOffset(offset: Double) {
    canCoderConfig.MagnetSensor.MagnetOffset = offset
    hasConfigChanged = true
  }

  override fun setInverted(isInverted: Boolean) {
    if (isInverted) {
      canCoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive
    } else {
      canCoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive
    }
    hasConfigChanged = true
  }

  override fun burnConfig() {
    if (hasConfigChanged) {
      encoder.getConfigurator().apply(canCoderConfig)
      hasConfigChanged = false
    }
  }

  override fun factoryDefaults() {}

  override fun clearFaults() {
    encoder.clearStickyFaults()
  }

  override fun getEncoder(): Any {
    return encoder
  }
}
