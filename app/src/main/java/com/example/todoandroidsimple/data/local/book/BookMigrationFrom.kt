package com.example.todoandroidsimple.data.local.book

import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@DeleteColumn.Entries(
    DeleteColumn(
        tableName = "books", columnName = "thumbnailBase642"
    )
)
interface  BookMigrationFrom3to4 : AutoMigrationSpec

@RenameColumn(
    tableName = "books",
    fromColumnName = "thumbnailBase64",
    toColumnName = "thumbnailLocalUri"
)
interface  BookMigrationFrom4to5 : AutoMigrationSpec
