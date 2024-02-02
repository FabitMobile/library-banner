package ru.fabit.banner

interface BannerAction {
    object Close : BannerAction
    object More : BannerAction
}