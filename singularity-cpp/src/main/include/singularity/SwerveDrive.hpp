/*
 * Copyright (C), 2023 Codex Microsystems and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

#pragma once

#include <frc/drive/RobotDriveBase.h>

namespace singularity {

class SwerveDrive : public RobotDriveBase {
 public:
  void Drive();
}

}  // namespace singularity
