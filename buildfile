require 'buildr/git_auto_version'

desc 'MMVP: Enhance the activities and places library'
define 'gwt-mmvp' do
  project.group = 'org.realityforge.gwt.mmvp'
  compile.options.source = '1.7'
  compile.options.target = '1.7'
  compile.options.lint = 'all'

  project.version = ENV['PRODUCT_VERSION'] if ENV['PRODUCT_VERSION']

  pom.add_apache2_license
  pom.add_github_project('realityforge/gwt-mmvp')
  pom.add_developer('realityforge', 'Peter Donald')
  pom.provided_dependencies.concat [:javax_annotation, :javax_inject]

  compile.with :javax_annotation,
               :gwt_user,
               :gwt_gin,
               :javax_inject

  package(:jar).include("#{_(:source, :main, :java)}/*")
  package(:sources)
  package(:javadoc)

  iml.add_jruby_facet
end
