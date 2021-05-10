@file:Suppress("MemberVisibilityCanBePrivate")

object Groups {
    const val ANDROID = "ru.nobird.android"
    const val APP = "ru.nobird.app"
}

object SubGroups {
    const val CORE = "core"
    const val DOMAIN = "domain"
    const val PRESENTATION = "presentation"
    const val VIEW = "view"
}

object Model {
    const val GROUP = "${Groups.APP}.${SubGroups.CORE}"
    const val VERSION = "1.0.7"
    const val NAME = "model"
    const val LIBRARY_NAME = "$GROUP:$NAME:$VERSION"
}