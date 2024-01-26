# Configuration file for the Sphinx documentation builder.
#
# This file only contains a selection of the most common options. For a full
# list see the documentation:
# https://www.sphinx-doc.org/en/master/usage/configuration.html

# -- Path setup --------------------------------------------------------------

# If extensions (or modules to document with autodoc) are in another directory,
# add these directories to sys.path here. If the directory is relative to the
# documentation root, use os.path.abspath to make it absolute, like shown here.
#
# import os
# import sys
# sys.path.insert(0, os.path.abspath('.'))


# -- Project information -----------------------------------------------------

project = 'Singularity'
copyright = '2023, Codex Microsystems and contributors. This work is licensed under the terms of the Creative Commons Attribution 4.0 International License.'
author = 'Codex Microsystems and contributors'


# -- General configuration ---------------------------------------------------

# Add any Sphinx extension module names here, as strings. They can be
# extensions coming with Sphinx (named 'sphinx.ext.*') or your custom
# ones.
extensions = [
  "sphinx_design",
  "sphinxext.remoteliteralinclude",
  "hoverxref.extension",
  "notfound.extension",
]

hoverxref_roles = ["term"]

# Add any paths that contain templates here, relative to this directory.
templates_path = ['_templates']

# List of patterns, relative to source directory, that match files and
# directories to ignore when looking for source files.
# This pattern also affects html_static_path and html_extra_path.
exclude_patterns = []


# -- Options for HTML output -------------------------------------------------

# The theme to use for HTML and HTML Help pages.  See the documentation for
# a list of builtin themes.
#
html_theme = "furo"

# Add any paths that contain custom static files (such as style sheets) here,
# relative to this directory. They are copied after the builtin static files,
# so a file named "default.css" will overwrite the builtin "default.css".
html_static_path = ['_static']

# URL favicon
html_favicon = "assets/favicon.png"

# URL title
html_title = 'Singularity'

html_theme_options = {
  "sidebar_hide_name": True,
  "light_logo": "logo-light-mode.png",
  "dark_logo": "logo-dark-mode.png",
    "light_css_variables": {
      "color-brand-primary": "#689d6a",
      "color-brand-content": "#689d6a"
    },
    "dark_css_variables": {
      "color-brand-primary": "#8ec07c",
      "color-brand-content": "#8ec07c"
    },
}
