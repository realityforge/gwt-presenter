require 'buildr/git_auto_version'

desc 'This library provides a "presenter" API implementation for GWT.'
define 'gwt-presenter' do
  project.group = 'org.realityforge.gwt.mmvp'
  compile.options.source = '1.7'
  compile.options.target = '1.7'
  compile.options.lint = 'all'

  project.version = ENV['PRODUCT_VERSION'] if ENV['PRODUCT_VERSION']

  pom.add_apache_v2_license
  pom.add_github_project('realityforge/gwt-mmvp')
  pom.add_developer('realityforge', 'Peter Donald')
  pom.add_developer('randomeizer', 'David Peterson', 'david@randombits.org', ['Original Developer'])

  pom.provided_dependencies.concat [:javax_annotation, :javax_inject]

  compile.with :javax_annotation,
               :gwt_user,
               :gwt_gin,
               :javax_inject,
               :google_guice,
               :google_guice_assistedinject,
               :aopalliance


  package(:jar).include("#{_(:source, :main, :java)}/*")
  package(:sources)
  package(:javadoc)

  iml.add_jruby_facet
end
