# Laravel Generator

![Build](https://github.com/GooGee/Laravel-Generator/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/15276.svg)](https://plugins.jetbrains.com/plugin/15276)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/15276.svg)](https://plugins.jetbrains.com/plugin/15276)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [x] Get familiar with the [template documentation][template].
- [x] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml) and [sources package](/src/main/kotlin).
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [x] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [x] Set the Plugin ID in the above README badges.
- [x] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

To keep everything working, do not remove `<!-- ... -->` sections. 

<!-- Plugin description -->


## Laravel CRUD scaffold generator.


## Features

- Convert Database schema to Laravel Migration
- Run artisan command
- Design database tables
- Define model factories
- Manage model relations
- Custom layer (Controller, Model, Repository, etc.)
- Custom template (with syntax: `for`, `if`, etc.)
- Generate Controller, Migration, Model, Test, etc.


## Guide

[guide](https://github.com/GooGee/Code-Generator-Page/blob/main/docs/guide.md)


## Where is it?

PHPStorm Menu Bar -> Tools -> Code Generator

![GUI](https://github.com/GooGee/Code-Generator/raw/main/image/v3.png)


<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Laravel-Generator"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/GooGee/Laravel-Generator/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
