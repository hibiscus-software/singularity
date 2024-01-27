/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

#pragma once

#include "singularity/imu/SwerveIMU.hpp"

namespace singularity {
class CANCoderSwerveEncoder : SwerveIMU {
 public:

 private:
 CANcoder canCoder;

 CANcoderConfiguration canCoderConfig;
}
}  // namespace singularity
