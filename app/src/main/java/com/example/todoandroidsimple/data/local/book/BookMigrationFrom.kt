package com.example.todoandroidsimple.data.local.book

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@RenameColumn(
    tableName = "books",
    fromColumnName = "thumbnailBase64",
    toColumnName = "thumbnailLocalUri"
)
class BookMigrationFrom4to5 : AutoMigrationSpec