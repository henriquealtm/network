package br.com.henriquealtmayer.network.list.livedata.domain.map

import br.com.henriquealtmayer.network.commons.handleOptional
import br.com.henriquealtmayer.network.list.livedata.data.model.HeroResponse
import br.com.henriquealtmayer.network.list.livedata.domain.model.Hero

fun HeroResponse.toHero() = Hero(
    id.handleOptional(),
    name.handleOptional(),
    description.handleOptional(),
    thumbnail?.getUrl().handleOptional()
)

fun List<HeroResponse>.toHeroList() = map { it.toHero() }